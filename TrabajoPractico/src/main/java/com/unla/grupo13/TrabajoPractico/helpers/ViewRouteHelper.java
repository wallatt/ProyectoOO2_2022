package com.unla.grupo13.TrabajoPractico.helpers;

public class ViewRouteHelper {

	
	// Aulas y Edificios
	
		public static String EDIFICIO_INDEX="edificio/index";
		public static String LABORATORIO_INDEX="aula/index";
		public static String TRADICIONAL_INDEX="aula/indexTradicional";
		public static String VER_TRADICIONALES="aula/verTradicionales";
		public static String VER_LABS="aula/verLabs";
		
		
		//Pedidos
		public static String NUEVO_PEDIDO="pedidos/nuevo";
		public static String PEDIDOS_ROOT="pedidos/pedidos";
		public static String PEDIDOS_OK="pedidos/okPedido";
		
		public static String GESTION_PEDIDOS="pedidos/gestionpedidos";
		public static String GESTION_ESPACIOS="pedidos/aulaasignada";
		public static String PEDIDOS_ASIGNADO="pedidos/asignado";
		
		

		/**** Views ****/
		//HOME
		
		public static String HOME="home/index";
		//USER
		public final static String INDEX = "/user";
		public final static String USER_INDEX ="user/exito";
		public final static String USER_NEW = "user/registro";
		public final static String DATOS_USER="user/datosuser";
		public final static String USER_EDITAR="user/editar";
		public final static String USER_LOGIN = "user/login";
		public final static String USER_LOGOUT = "user/logout";
		public final static String EXITO = "user/exito";
		public final static String USER_ASISTENTE= "user/asistente";
		public final static String EXITO_ASISTENTE="/user/asistente/exitoasistente";
		public final static String EXITO_USER_ASISTENTE="user/exitoasistente";
		/**** Redirects ****/
		public final static String USER_ROOT = "/user";
		public static final String PEDIDO_EDITAR = "pedidos/editar";
		public static final String PERFIL = "home/perfil";


		// espacios
		public static String ESPACIO_NUEVO="espacio/nuevo";
		public static String ESPACIO_OK="espacio/okEspacio";
		public static String ESPACIOS_VALIDOS="espacio/espacios";
		public static String ESPACIOS_VER="espacio/verEspacios";
		public static String ESPACIOS_VER_LAB="espacio/VerEspaciosLab";
}
