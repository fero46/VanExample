package agent;

import lombok.Data;
import model.VanCarrierModel;
import famos.agent.Agent;
import famos.agent.MultiAgentModel;

@Data
public class Van extends Agent
{
    long         maxTransport = 30;

    private long inhalt;

    public Van(VanCarrierModel model, String name)
    {
        super(model, name, true);
        setBehaviour(new VanBehaviour());
    }
    
    private void init()
    {
        System.out.print("Init");
    }

}
