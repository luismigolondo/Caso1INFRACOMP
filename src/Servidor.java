/**
 * 
 */

/**
 * @author luisgomez
 *
 */
public class Servidor extends Thread{

	private Buffer buffer;

	private int id;

	public Servidor (int id, Buffer b) {
		this.buffer = b;
		this.id = id;
	}

	public void run () {
		System.out.println("Servidor " + id + " activado");
		while(true) {
			Mensaje retirado = buffer.retirar();
			//Se hace while true para que siempre este intentando retirar mensajes si es que si hay mensajes en el buffer...
			if(retirado != null)
			{
				synchronized (retirado) {
					retirado.notify();
					retirado.cambiar();		
					System.out.println("Servidor "+ this.id + " retiró el mensaje: "+ retirado.getId() + " Del cliente: "+ retirado.getCliente().getIdentificador());
				}
			}
			else
				yield();
			if(buffer.getMensajesRestantes() == 0)
			{
				System.out.println("El servidor " + id + " termino");
				break;
			}
		}
	}

}
