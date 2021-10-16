package com.rachadel.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Manoel Rachadel Neto
 * @since 12 de out. de 2021
 */

@Getter
@AllArgsConstructor
public enum OperationType {

	COMPRA_A_VISTA(0, "COMPRA A VSTA"), COMPRA_PARCELADA(1, "COMPRA PARCELADA"), SAQUE(2, "SAQUE"),
	PAGAMENTO(3, "PAGAMENTO");

	private Integer id;
	private String description;
}
