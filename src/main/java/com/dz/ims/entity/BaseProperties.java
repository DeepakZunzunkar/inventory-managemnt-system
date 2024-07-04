package com.dz.ims.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Builder
@Embeddable
@Access(AccessType.FIELD)
public class BaseProperties {


	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	public BaseProperties(String createdBy, Timestamp createdAt, String updatedBy, Timestamp updatedAt) {
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
	}
}
