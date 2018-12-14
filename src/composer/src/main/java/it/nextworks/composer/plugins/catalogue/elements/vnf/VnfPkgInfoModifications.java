/*
 * DRAFT - SOL005 - VNF Package Management Interface
 * DRAFT - SOL005 - VNF Package Management Interface IMPORTANT: Please note that this file might be not aligned to the current version of the ETSI Group Specification it refers to and has not been approved by the ETSI NFV ISG. In case of discrepancies the published ETSI Group Specification takes precedence. Please report bugs to https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 * OpenAPI spec version: 2.4.1
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package it.nextworks.composer.plugins.catalogue.elements.vnf;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import it.nextworks.composer.plugins.catalogue.elements.nsd.KeyValuePairs;

import java.util.Objects;

/**
 * VnfPkgInfoModifications
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-21T15:10:42.557+01:00")
public class VnfPkgInfoModifications {
    @JsonProperty("userDefinedData")
    private KeyValuePairs userDefinedData = null;

    @JsonProperty("operationalState")
    private PackageOperationalStateType operationalState = null;

    public VnfPkgInfoModifications userDefinedData(KeyValuePairs userDefinedData) {
        this.userDefinedData = userDefinedData;
        return this;
    }

    /**
     * Get userDefinedData
     *
     * @return userDefinedData
     **/
    @ApiModelProperty(value = "")
    public KeyValuePairs getUserDefinedData() {
        return userDefinedData;
    }

    public void setUserDefinedData(KeyValuePairs userDefinedData) {
        this.userDefinedData = userDefinedData;
    }

    public VnfPkgInfoModifications operationalState(PackageOperationalStateType operationalState) {
        this.operationalState = operationalState;
        return this;
    }

    /**
     * Get operationalState
     *
     * @return operationalState
     **/
    @ApiModelProperty(value = "")
    public PackageOperationalStateType getOperationalState() {
        return operationalState;
    }

    public void setOperationalState(PackageOperationalStateType operationalState) {
        this.operationalState = operationalState;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VnfPkgInfoModifications vnfPkgInfoModifications = (VnfPkgInfoModifications) o;
        return Objects.equals(this.userDefinedData, vnfPkgInfoModifications.userDefinedData) &&
            Objects.equals(this.operationalState, vnfPkgInfoModifications.operationalState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDefinedData, operationalState);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VnfPkgInfoModifications {\n");

        sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
        sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
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

