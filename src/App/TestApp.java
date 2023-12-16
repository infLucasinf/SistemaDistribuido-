package App;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestApp extends UnicastRemoteObject implements RepInterface {
    private List<Membro> membros = new ArrayList<>();
    private int liderId;

    protected TestApp() throws RemoteException {
        super();
        iniciarGrupo();
    }

    private void iniciarGrupo() {
     
        liderId = 0;
        membros.add(new Membro(0, false, false)); 
        membros.add(new Membro(1, true, true)); 
        membros.add(new Membro(2, false, true)); 
    }
    

    @Override
    public void sendDataToReplicas(String data, int senderId) throws RemoteException {
        if (verificarLider(senderId)) {
            System.out.println("Réplica " + senderId + " enviando dados: " + data);
            // Lógica de replicação
            for (Membro membro : membros) {
                if (membro.isAtivo() && membro.getId() != senderId) {
                    RepInterface replica = null;
					try {
						replica = connectToReplica(membro.getId());
					} catch (RemoteException | NotBoundException e) {
						
						e.printStackTrace();
					}
                    replica.receberReplicacao1(data, senderId);
                }
            }
        }
    }

        
    

    @Override
    public boolean verificarLider(int id) throws RemoteException {
        return liderId == id;
    }

    @Override
    public void solicitarAdesao(int novoMembroId) throws RemoteException {
        membros.add(new Membro(novoMembroId, false, true));
        System.out.println("Membro " + novoMembroId + " solicitou adesão ao grupo.");
        verificarLider(); 
    }

    @Override
    public void iniciarEleicao(int candidatoId) throws RemoteException {
        System.out.println("Eleição iniciada por membro " + candidatoId);
        
    try {
        TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    
    System.out.println("Eleição concluída, membro " + candidatoId + " se torna o novo líder.");
    
    liderId = candidatoId;
    try {
		notificarNovoLider(candidatoId);
	} catch (RemoteException | NotBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

    
    
    @Override
    public void receberNotificacao(String mensagem) throws RemoteException {
        System.out.println("Notificação recebida: " + mensagem);
    }

    private void verificarLider() throws RemoteException {
       
        if (!membros.get(liderId).isAtivo()) {
            try {
				iniciarEleicao(liderId);
			} catch (RemoteException e) {
			
				e.printStackTrace();
			} catch (Exception e) {
			
				e.printStackTrace();
			}
        }
    }

    private void notificarNovoLider(int novoLiderId) throws RemoteException, NotBoundException {
        System.out.println("Notificando membros sobre o novo líder: " + novoLiderId);
        for (Membro membro : membros) {
            if (membro.isAtivo() && membro.getId() != novoLiderId) {
                RepInterface replica = connectToReplica(membro.getId());
                replica.receberNotificacao("Novo líder: " + novoLiderId);
            }
        }
    }

    private RepInterface connectToReplica(int id) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        return (RepInterface) registry.lookup("ReplicationServer" + id);
    }

    public static void main(String[] args) {
        try {
            TestApp server = new TestApp();
            server.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void receberReplicacao1(String data, int senderId) throws RemoteException {
        System.out.println("Réplica " + liderId + " recebeu dados replicados de " + senderId + ": " + data);
        
        // 
        processarDadosReplicados(data);
    }

    private void processarDadosReplicados(String data) {
        
        System.out.println("Processando dados replicados: " + data);
       
    }

    private void startServer() {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ReplicationServer" + liderId, this);
            System.out.println("Servidor RMI pronto para receber dados...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void receberReplicacao(String data, int senderId) throws RemoteException {
	
		
	}
}
