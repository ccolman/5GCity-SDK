/*
* Copyright 2018 Nextworks s.r.l.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package it.nextworks.composer.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.nextworks.composer.executor.interfaces.ServiceManagerProviderInterface;
import it.nextworks.composer.executor.repositories.SDKServiceRepository;
import it.nextworks.sdk.SDKFunction;
import it.nextworks.sdk.SDKService;
import it.nextworks.sdk.enums.StatusType;
import it.nextworks.sdk.exceptions.AlreadyPublishedServiceException;
import it.nextworks.sdk.exceptions.ExistingEntityException;
import it.nextworks.sdk.exceptions.NotExistingEntityException;
import it.nextworks.sdk.exceptions.NotPublishedServiceException;

@Service
public class ServiceManager implements ServiceManagerProviderInterface{
	
	private static final Logger log = LoggerFactory.getLogger(ServiceManager.class);
	
	@Autowired
	private SDKServiceRepository serviceRepository;
	
	
	public ServiceManager() {}
	
	
	@Override
	public SDKService getServiceByUuid(UUID id) throws NotExistingEntityException {
		log.info("Request for service with UUID: " + id);
		Optional<SDKService> service = serviceRepository.findByUuid(id);
		if (service.isPresent()) {
			return service.get();
		} else {
			log.error("Service with UUID " + id + " not found");
			throw new NotExistingEntityException("Service with UUID " + id + " not found");
		}
	}

	@Override
	public List<SDKService> getServices() {
		log.info("Request for all service stored in database");
		List<SDKService> services = serviceRepository.findAll();
		if(services != null ) {
			return services;
		} else {
			log.error("No services are available");
			return null;
		}
	}

	@Override
	public List<SDKService> getServicesUsingFunction(UUID functionId) {
		log.info("Request for all services which are using function with uuid: " + functionId);
		List<SDKService> serviceList = new ArrayList<>();
		List<SDKService> services = serviceRepository.findAll();
		for(SDKService service : services) {
			for (SDKFunction function : service.getFunctions())
				if(function.getUuid().equals(functionId)) {
					log.debug("Service " + service.getId() + " has been found");
					serviceList.add(service);
					break;
				}
		}
		return serviceList;
	}

	@Override
	public String createService(SDKService service) throws ExistingEntityException{
		log.info("Storing into database a new service with ID: " + service.getId());
		Optional<SDKService> srv = serviceRepository.findById(service.getId());
		if(srv.isPresent()) {
			log.error("Service id " + service.getId()+ " already present in database");
			throw new ExistingEntityException("Service id " + service.getId()+ " already present in database");
		}
		//TODO Check if is valid
		log.debug("Storing into database service with id: " + service.getId());
		serviceRepository.saveAndFlush(service);
		return service.getId().toString();
	}

	@Override
	public String updateService(SDKService service) throws NotExistingEntityException {
		log.info("Updating an existing service with id: " + service.getId());
		//TODO Check if service exists
		Optional<SDKService> srv = serviceRepository.findById(service.getId());
		if(!srv.isPresent()) {
			log.error("Service id " + service.getId()+ " already present in database");
			throw new NotExistingEntityException("Service id " + service.getId()+ " not present in database");
		}
		//TODO Check if is valid
		log.debug("Updating into database service with id: " + service.getId());
		serviceRepository.saveAndFlush(service);
		return service.getId().toString();
	}


	@Override
	public SDKService getServiceById(Long id) throws NotExistingEntityException {
		log.info("Request for service with ID: " + id);
		Optional<SDKService> service = serviceRepository.findById(id);
		if (service.isPresent()) {
			return service.get();
		} else {
			log.error("Service with UUID " + id + " not found");
			throw new NotExistingEntityException("Service with ID " + id + " not found");
		}
	}


	@Override
	public void deleteService(UUID serviceId) throws NotExistingEntityException {
		log.info("Request for deletion of service with uuid: " + serviceId);
		Optional<SDKService> service = serviceRepository.findByUuid(serviceId);
		if(service.isPresent()) {
			serviceRepository.delete(service.get());
		} else {
			log.error("Service with UUID " + serviceId + " not found");
			throw new NotExistingEntityException("Service with ID " + serviceId + " not found");
		}
	}


	@Override
	public void publishService(UUID serviceId) throws NotExistingEntityException, AlreadyPublishedServiceException {
		log.info("Request for publication of service with uuid: " + serviceId);
		//Check if service exists
		Optional<SDKService> optService = serviceRepository.findByUuid(serviceId);
		if(!optService.isPresent()) {
			log.error("The Service with UUID: " + serviceId + " is not present in database");
			throw new NotExistingEntityException("The Service with UUID: " + serviceId + " is not present in database");
		} else {
			//Check if is already published
			SDKService service = optService.get();
			if(service.getStatus() == StatusType.COMMITTED) {
				log.error("The service identified by UUID: " + serviceId + " has been already published");
				throw new AlreadyPublishedServiceException("The service identified by UUID: " + serviceId + " has been already published");
			} else {
				//A thread will be created to handle this request in order to perform it asynchronously.
				service.setStatus(StatusType.COMMITTED);
				serviceRepository.saveAndFlush(service);
				throw new NotYetImplementedException("Method not yet implemented");
			}
		}
	}


	@Override
	public void unPublishService(UUID serviceId) throws NotExistingEntityException, NotPublishedServiceException {
		log.info("Request for delete the publication of service with uuid: " + serviceId);
		Optional<SDKService> optService = serviceRepository.findByUuid(serviceId);
		if(!optService.isPresent()) {
			log.error("The Service with UUID: " + serviceId + " is not present in database");
			throw new NotExistingEntityException("The Service with UUID: " + serviceId + " is not present in database");
		} else {
			//Check if is already published
			SDKService service = optService.get();
			if(service.getStatus() == StatusType.SAVED) {
				log.error("The service identified by UUID: " + serviceId + " has not been published yet");
				throw new NotPublishedServiceException("The service identified by UUID: " + serviceId + " has been already published");
			} else {
				//A thread will be created to handle this request in order to perform it asynchronously.
				service.setStatus(StatusType.SAVED);
				serviceRepository.saveAndFlush(service);
				throw new NotYetImplementedException("Method not yet implemented");
			}
		}
	}

	
}
