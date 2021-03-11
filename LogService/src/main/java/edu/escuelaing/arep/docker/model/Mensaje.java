package edu.escuelaing.arep.docker.model;

import com.mongodb.BasicDBObject;
import java.util.Date;

public class Mensaje {

	private String mensaje;
	private Date fecha;

	public Mensaje() {
	}

	/**
	 * Crea in objeto Mensaje
	 * 
	 * @param mensaje cadena a insertar
	 * @param fecha  recha de insersión de la cadena
	 */
	public Mensaje(String mensaje, Date fecha) {
		this.mensaje = mensaje;
		this.fecha = fecha;
	}

	/**
	 * Transforma un objecto MongoDB a un objeto Java
	 * 
	 * @param dBObject objeto MongoDB
	 */
	public Mensaje(BasicDBObject dBObject) {
		this.mensaje = dBObject.getString("mensaje");
		this.fecha = dBObject.getDate("fecha");
	}

	/**
	 * Transforma de objeto Mongo a objeto mensjae Java
	 * 
	 * @return objeto Mongo creado
	 */
	public BasicDBObject toDBObjectData() {
		BasicDBObject dBObjectData = new BasicDBObject();
		dBObjectData.append("mensaje", this.getMensaje());
		dBObjectData.append("fecha", this.getFecha());
		return dBObjectData;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Mensaje: " + this.getMensaje() + " Fecha: " + this.getFecha();
	}
}