package agent;

import java.util.List;

import model.VanCarrierModel;

import signals.EntladenSignal;
import signals.LadenSignal;

import famos.agent.Behaviour;

public class VanBehaviour extends Behaviour
{

    @Override
    public void start()
    {
        super.start();
        Van v = (Van) getAgent();
        VanCarrierModel model = (VanCarrierModel) v.getModel();
        model.getLadestelle().insert(v);
        if(!model.getLaden().isCurrent()) {
            model.getLaden().activate(v.currentTime());
        }
    }

    @Override
    public void handle(List signals)
    {
        for (int i = 0; i < signals.size(); i++)
        {
            Object s = signals.get(i);
            if (s instanceof EntladenSignal)
            {
                Van v = (Van) getAgent();
                VanCarrierModel model = (VanCarrierModel) v.getModel();
                model.getEntladestelle().insert(v);
                System.out.println(v.getName() + " ist bereit zum Entladen :" +v.currentTime());
                if(!model.getEntladen().isCurrent()) {
                    
                    model.getEntladen().activate(v.currentTime());
                }
            }
            else if (s instanceof LadenSignal)
            {
                Van v = (Van) getAgent();
                VanCarrierModel model = (VanCarrierModel) v.getModel();
                model.getLadestelle().insert(v);
                System.out.println(v.getName() + " ist bereit zum Laden :" +v.currentTime());
                if(!model.getEntladen().isCurrent()) {
                    model.getLaden().activate(v.currentTime());                    
                }
            }
        }
    }
}
