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
package it.nextworks.sdk;
import com.fasterxml.jackson.annotation.*;
import it.nextworks.sdk.enums.MonitoringParameterType;

import javax.persistence.*;


/**
 * BaseMonitoringParameter
 * <p>
 *
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "parameterType", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = MonParamAggregated.class, 	name = "AGGREGATED"),
    @JsonSubTypes.Type(value = MonParamFunction.class, 	name = "FUNCTION"),
    @JsonSubTypes.Type(value = MonParamImported.class, 	name = "IMPORTED"),
    @JsonSubTypes.Type(value = MonParamTransformed.class, 	name = "TRANSFORMED"),
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "parameterId",
    "parameterType"
})
@Entity
public abstract class MonitoringParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private MonitoringParameterType parameterType;

    private String name;

    @ManyToOne
    private SdkService sdkServiceExt;

    @ManyToOne
    private SdkService sdkServiceInt;

    @ManyToOne
    private SdkFunction sdkFunction;

    public MonitoringParameter(){
        //JPA only
    }

    @JsonProperty("parameterType")
    public MonitoringParameterType getParameterType() {
        return parameterType;
    }

    @JsonProperty("parameterType")
    public void setParameterType(MonitoringParameterType parameterType) {
        this.parameterType = parameterType;
    }

    @JsonProperty("id")
    public String getParameterId() {
        return id.toString();
    }

    @JsonProperty("id")
    public void setParameterId(String parameterId) {
        this.id = Long.valueOf(parameterId);
    }

    @JsonIgnore
    public Long getId() { return id; }

    @JsonIgnore
    public void setId(Long id) { this.id = id; }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public SdkService getSdkServiceExt() {
        return sdkServiceExt;
    }

    @JsonIgnore
    public void setSdkServiceExt(SdkService sdkService) {
        this.sdkServiceExt = sdkService;
    }

    @JsonIgnore
    public SdkService getSdkServiceInt() {
        return sdkServiceInt;
    }

    @JsonIgnore
    public void setSdkServiceInt(SdkService sdkService) {
        this.sdkServiceInt = sdkService;
    }

    @JsonIgnore
    public SdkFunction getSdkFunction() { return sdkFunction; }

    @JsonIgnore
    public void setSdkFunction(SdkFunction sdkFunction) { this.sdkFunction = sdkFunction; }

    @JsonIgnore
    public boolean isValid() {//TODO remove check on parameterType
        return parameterType != null && (parameterType.equals(MonitoringParameterType.FUNCTION) || parameterType.equals(MonitoringParameterType.IMPORTED))
            && name != null && name.length() > 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MonitoringParameter.class.getName()).append('[');
        sb.append("parameterType");
        sb.append('=');
        sb.append(((this.parameterType == null)?"<null>":this.parameterType));
        sb.append(',');
        sb.append("parameterId");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.parameterType == null)? 0 :this.parameterType.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MonitoringParameter) == false) {
            return false;
        }
        MonitoringParameter rhs = ((MonitoringParameter) other);
        return (((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id)))
            &&((this.parameterType == rhs.parameterType)||((this.parameterType!= null)&&this.parameterType.equals(rhs.parameterType)))
            &&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))));
    }
}
