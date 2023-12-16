package App;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ReplicClient {

    private static final int CANDIDATO_ID = 1; // ID do membro que deseja se tornar líder

    public static void main(String[] args) {
        String serverAddress = "localhost";
        String serverPort = "1099";
        String data = "Dados a serem replicados";

        try {
            RepInterface replica = connectToReplica(serverAddress, serverPort);

            // Verifica se o líder está ativo
            if (!replica.verificarLider(0)) {
                System.out.println("Membro " + CANDIDATO_ID + " iniciando eleição...");

              
              replica.iniciarEleicao(CANDIDATO_ID);

               
                if (replica.verificarLider(CANDIDATO_ID)) {
                    System.out.println("Membro " + CANDIDATO_ID + " tornou-se líder!");
                } else {
                    System.out.println("Membro " + CANDIDATO_ID + " não foi eleito líder.");
                }
            } else {
                System.out.println("Membro " + CANDIDATO_ID + " já é líder.");
            }

            // Continua com a lógica de enviar dados para réplicas
            replica.sendDataToReplicas(data, CANDIDATO_ID);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static RepInterface connectToReplica(String serverAddress, String serverPort) throws Exception {
        Registry registry = LocateRegistry.getRegistry(serverAddress, Integer.parseInt(serverPort));
        return (RepInterface) registry.lookup("ReplicationServer0");
    }
}
