/**
 * 
 */

/**
 * @author luisgomez
 *
 */
public class Mensaje {

	private Buffer buffer;

	private int id;

	//El contenido inicial siempre sera 0, con respuesta sera 1...
	private int contenido = 0;

	public Mensaje(int id, Buffer b) {
		this.id =  id;
		this.buffer = b;
	}

	/**
	 * @return the buffer
	 */
	public Buffer getBuffer() {
		return buffer;
	}

	/**
	 * @param buffer the buffer to set
	 */
	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the contenido
	 */
	public int getContenido() {
		return contenido;
	}

	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(int contenido) {
		this.contenido = contenido;
	}

	public void cambiar() {
		System.out.println("Mensaje " + id + " cambiado");
		contenido++;
	}

}
