/*
 * DRAFT - SOL005 - NSD Management Interface
 * DRAFT - SOL005 - NSD Management Interface IMPORTANT: Please note that this file might be not aligned to the current version of the ETSI Group Specification it refers to and has not been approved by the ETSI NFV ISG. In case of discrepancies the published ETSI Group Specification takes precedence. Please report bugs to https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 * OpenAPI spec version: 2.4.1
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package it.nextworks.composer.plugins.catalogue.elements.nsd;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import java.util.Objects;
import java.util.UUID;

/**
 * This type represents a PNFD management notification, which informs the receiver of the deletion of an on-boarded PNFD. The notification shall comply with the provisions defined in Table 5.5.2.15-1. The support of this notification is mandatory. The notification is triggered when an on-boarded PNFD is deleted.
 */
@ApiModel(description = "This type represents a PNFD management notification, which informs the receiver of the deletion of an on-boarded PNFD. The notification shall comply with the provisions defined in Table 5.5.2.15-1. The support of this notification is mandatory. The notification is triggered when an on-boarded PNFD is deleted.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-21T15:01:43.121+01:00")
public class PnfdDeletionNotification {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("notificationType")
    private NotificationType notificationType = null;

    @JsonProperty("subscriptionId")
    private UUID subscriptionId = null;

    @JsonProperty("timeStamp")
    private DateTime timeStamp = null;

    @JsonProperty("pnfdInfoId")
    private UUID pnfdInfoId = null;

    @JsonProperty("pnfdId")
    private UUID pnfdId = null;

    @JsonProperty("_links")
    private PnfdmLinks links = null;

    public PnfdDeletionNotification id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \&quot;id\&quot; attribute of all these notifications shall have the same value.
     *
     * @return id
     **/
    @ApiModelProperty(required = true, value = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PnfdDeletionNotification notificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    /**
     * Discriminator for the different notification types. Shall be set to \&quot;PnfdDeletionNotification \&quot; for this notification type.
     *
     * @return notificationType
     **/
    @ApiModelProperty(required = true, value = "Discriminator for the different notification types. Shall be set to \"PnfdDeletionNotification \" for this notification type.")
    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public PnfdDeletionNotification subscriptionId(UUID subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    /**
     * Get subscriptionId
     *
     * @return subscriptionId
     **/
    @ApiModelProperty(value = "")
    public UUID getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(UUID subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public PnfdDeletionNotification timeStamp(DateTime timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    /**
     * Date-time of the generation of the notification.
     *
     * @return timeStamp
     **/
    @ApiModelProperty(required = true, value = "Date-time of the generation of the notification.")
    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(DateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public PnfdDeletionNotification pnfdInfoId(UUID pnfdInfoId) {
        this.pnfdInfoId = pnfdInfoId;
        return this;
    }

    /**
     * Get pnfdInfoId
     *
     * @return pnfdInfoId
     **/
    @ApiModelProperty(required = true, value = "")
    public UUID getPnfdInfoId() {
        return pnfdInfoId;
    }

    public void setPnfdInfoId(UUID pnfdInfoId) {
        this.pnfdInfoId = pnfdInfoId;
    }

    public PnfdDeletionNotification pnfdId(UUID pnfdId) {
        this.pnfdId = pnfdId;
        return this;
    }

    /**
     * Get pnfdId
     *
     * @return pnfdId
     **/
    @ApiModelProperty(required = true, value = "")
    public UUID getPnfdId() {
        return pnfdId;
    }

    public void setPnfdId(UUID pnfdId) {
        this.pnfdId = pnfdId;
    }

    public PnfdDeletionNotification links(PnfdmLinks links) {
        this.links = links;
        return this;
    }

    /**
     * This type represents the links to resources that a PNFD management notification can contain.
     *
     * @return links
     **/
    @ApiModelProperty(required = true, value = "This type represents the links to resources that a PNFD management notification can contain.")
    public PnfdmLinks getLinks() {
        return links;
    }

    public void setLinks(PnfdmLinks links) {
        this.links = links;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PnfdDeletionNotification pnfdDeletionNotification = (PnfdDeletionNotification) o;
        return Objects.equals(this.id, pnfdDeletionNotification.id) &&
            Objects.equals(this.notificationType, pnfdDeletionNotification.notificationType) &&
            Objects.equals(this.subscriptionId, pnfdDeletionNotification.subscriptionId) &&
            Objects.equals(this.timeStamp, pnfdDeletionNotification.timeStamp) &&
            Objects.equals(this.pnfdInfoId, pnfdDeletionNotification.pnfdInfoId) &&
            Objects.equals(this.pnfdId, pnfdDeletionNotification.pnfdId) &&
            Objects.equals(this.links, pnfdDeletionNotification.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notificationType, subscriptionId, timeStamp, pnfdInfoId, pnfdId, links);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PnfdDeletionNotification {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
        sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
        sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
        sb.append("    pnfdInfoId: ").append(toIndentedString(pnfdInfoId)).append("\n");
        sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
        sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
