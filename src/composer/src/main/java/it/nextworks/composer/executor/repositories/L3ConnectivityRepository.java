/*
 * Copyright 2020 Nextworks s.r.l.
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
package it.nextworks.composer.executor.repositories;

import it.nextworks.sdk.L3Connectivity;
import it.nextworks.sdk.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface L3ConnectivityRepository extends JpaRepository<L3Connectivity, Long> {

    Optional<L3Connectivity> findById(Long id);


}
