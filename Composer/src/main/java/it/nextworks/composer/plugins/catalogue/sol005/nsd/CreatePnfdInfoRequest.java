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
package it.nextworks.composer.plugins.catalogue.sol005.nsd;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import it.nextworks.composer.plugins.catalogue.sol005.common.KeyValuePairs;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type creates a new PNF descriptor resource.
 */
@ApiModel(description = "This type creates a new PNF descriptor resource.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-23T16:31:35.952+02:00")

public class CreatePnfdInfoRequest {
	@JsonProperty("userDefinedData")
	private KeyValuePairs userDefinedData = null;

	public CreatePnfdInfoRequest userDefinedData(KeyValuePairs userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * Get userDefinedData
	 * 
	 * @return userDefinedData
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public KeyValuePairs getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(KeyValuePairs userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CreatePnfdInfoRequest createPnfdInfoRequest = (CreatePnfdInfoRequest) o;
		return Objects.equals(this.userDefinedData, createPnfdInfoRequest.userDefinedData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userDefinedData);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CreatePnfdInfoRequest {\n");

		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}