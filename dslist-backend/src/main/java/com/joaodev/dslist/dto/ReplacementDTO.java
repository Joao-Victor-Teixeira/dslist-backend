package com.joaodev.dslist.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO responável pela ordenação do game na lista")
public class ReplacementDTO {

	@Schema(description = "Index de posição do game na lista")
	private Integer sourceIndex;
	
	@Schema(description = "Index de destino do game na lista")
	private Integer destinationIndex;

	public Integer getSourceIndex() {
		return sourceIndex;
	}

	public void setSourceIndex(Integer sourceIndex) {
		this.sourceIndex = sourceIndex;
	}

	public Integer getDestinationIndex() {
		return destinationIndex;
	}

	public void setDestinationIndex(Integer destinationIndex) {
		this.destinationIndex = destinationIndex;
	}
}
