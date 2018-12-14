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

import java.util.Objects;

/**
 * PkgmLinks
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-21T15:10:42.557+01:00")
public class PkgmLinks {
    @JsonProperty("vnfPackage")
    private String vnfPackage = null;

    @JsonProperty("subscription")
    private String subscription = null;

    public PkgmLinks vnfPackage(String vnfPackage) {
        this.vnfPackage = vnfPackage;
        return this;
    }

    /**
     * Get vnfPackage
     *
     * @return vnfPackage
     **/
    @ApiModelProperty(value = "")
    public String getVnfPackage() {
        return vnfPackage;
    }

    public void setVnfPackage(String vnfPackage) {
        this.vnfPackage = vnfPackage;
    }

    public PkgmLinks subscription(String subscription) {
        this.subscription = subscription;
        return this;
    }

    /**
     * Get subscription
     *
     * @return subscription
     **/
    @ApiModelProperty(value = "")
    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PkgmLinks pkgmLinks = (PkgmLinks) o;
        return Objects.equals(this.vnfPackage, pkgmLinks.vnfPackage) &&
            Objects.equals(this.subscription, pkgmLinks.subscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vnfPackage, subscription);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PkgmLinks {\n");

        sb.append("    vnfPackage: ").append(toIndentedString(vnfPackage)).append("\n");
        sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
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

