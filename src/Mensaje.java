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
	
	private Cliente cliente;

	//El contenido inicial siempre sera 0, con respuesta sera 1...
	private int contenido = 0;

	public Mensaje(int id, Buffer b, Cliente c) {
		this.id =  id;
		this.buffer = b;
		this.cliente = c;
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
		contenido++;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
