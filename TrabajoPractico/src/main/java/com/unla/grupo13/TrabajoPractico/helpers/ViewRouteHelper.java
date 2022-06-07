package com.unla.grupo13.TrabajoPractico.helpers;

public class ViewRouteHelper {

	
	public static String HOME="home/index";
	public static String EDIFICIO_INDEX="edificio/index";
	public static String LABORATORIO_INDEX="aula/index";
	public static String TRADICIONAL_INDEX="aula/indexTradicional";

	public static String NUEVO_PEDIDO="pedidos/nuevo";
	public static String PEDIDOS_ROOT="pedidos/pedidos";
	public static String PEDIDOS_OK="pedidos/okPedido";
	public static String PEDIDO_EDITAR="pedidos/editar";

	public static String GESTION_PEDIDOS="pedidos/gestionpedidos";
	public static String GESTION_PEDIDOS_AULAS_LABORATORIO="pedidos/gestionpedidosAulasLaboratorio";
	public static String GESTION_PEDIDOS_AULAS_TRADICIONAL="pedidos/gestionpedidosAulasTradicional";

	public static String GESTION_ESPACIOS="pedidos/aulaasignada";



	/**** Views ****/
	//HOME
	public final static String INDEX = "/user";
	public final static String PERFIL="home/perfil";
	//USER
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


	// espacios
	public static String ESPACIO_NUEVO="espacio/nuevo";
	public static String ESPACIO_OK="espacio/okEspacio";

}
