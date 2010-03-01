package process;

import signals.LadenSignal;
import agent.Van;
import model.VanCarrierModel;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.SimTime;

public class EntladeStelle extends SimProcess
{

    long waren = 0;

    public EntladeStelle(VanCarrierModel owner, String name, boolean showInTrace)
    {
        super(owner, name, showInTrace);
    }

    @Override
    public void lifeCycle()
    {
        double time = 10.0;
        while (waren < 1000)
        {
            if (((VanCarrierModel) getModel()).getEntladestelle().isEmpty())
            {
                this.passivate();
            }
            else
            {
                Van v = (Van) ((VanCarrierModel) getModel()).getEntladestelle()
                        .first();
                ((VanCarrierModel) getModel()).getEntladestelle().remove(v);

                SimTime now = v.currentTime();

                waren += v.getInhalt();
                
                v.setInhalt(0);                
                SimTime duration = SimTime.add(new SimTime(time), now);
                v.scheduleAt(new LadenSignal(), duration);
                time+=20;
            }
        }
    }

}
