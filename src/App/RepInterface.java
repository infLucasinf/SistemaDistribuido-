package App;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RepInterface extends Remote {
    void sendDataToReplicas(String data, int senderId) throws RemoteException;
    boolean verificarLider(int id) throws RemoteException;
    void solicitarAdesao(int novoMembroId) throws RemoteException;
    void iniciarEleicao(int candidatoId) throws RemoteException;
    void receberReplicacao(String data, int senderId) throws RemoteException;
	void receberNotificacao(String mensagem) throws RemoteException;
	void receberReplicacao1(String data, int senderId) throws RemoteException; 
}
