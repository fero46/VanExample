package process;

import signals.EntladenSignal;
import agent.Van;
import model.VanCarrierModel;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.SimTime;

public class LadeStelle extends SimProcess
{

    long waren = 10000;

    public LadeStelle(VanCarrierModel owner, String name, boolean showInTrace)
    {
        super(owner, name, showInTrace);
    }

    @Override
    public void lifeCycle()
    {
        double time = 10;
        while (waren > 0)
        {
            if (((VanCarrierModel) getModel()).getLadestelle().isEmpty())
            {
                passivate();
            }
            else
            {
                Van v = (Van) ((VanCarrierModel) getModel()).getLadestelle()
                        .first();
                ((VanCarrierModel) getModel()).getLadestelle().remove(v);
                SimTime now = v.currentTime();

                long transport = waren % v.getMaxTransport();

                waren -= transport;

                v.setInhalt(transport);

                v.setInhalt(0);
                SimTime duration = SimTime.add(new SimTime(time), now);
                v.scheduleAt(new EntladenSignal(), duration);
                time+=40;

            }
        }
    }

}
