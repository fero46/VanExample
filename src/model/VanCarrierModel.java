package model;

import java.beans.PropertyChangeEvent;

import agent.Van;

import process.EntladeStelle;
import process.LadeStelle;

import lombok.Data;
import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.QueueBased;
import desmoj.core.simulator.QueueList;
import desmoj.core.simulator.QueueListFifo;
import desmoj.core.simulator.SimTime;
import famos.agent.MultiAgentModel;

@Data
public class VanCarrierModel extends MultiAgentModel
{

    QueueList     ladestelle;
    QueueList     entladestelle;
    EntladeStelle entladen;
    LadeStelle    laden;

    public VanCarrierModel(Model owner, String name, boolean showInReport,
            boolean showInTrace)
    {
        super(owner, name, showInReport, showInTrace);

    }

    @Override
    public String description()
    {
        return null;
    }

    @Override
    public void doInitialSchedules()
    {
        ladestelle = new QueueListFifo();
        ladestelle.setQueueBased(new Queue<Van>(this, "Lade Warteschlande", true, true));
        entladestelle = new QueueListFifo();
        entladestelle.setQueueBased(new Queue<Van>(this, "EntLade Warteschlande", true, true));
        entladen = new EntladeStelle(this, "Entladen", false);
        laden = new LadeStelle(this, "Laden", false);
        
        for (int i = 0; i < 100; i++)
        {
            Van v = new Van(this, "Van nr" + i);
            v.start(SimTime.now());

        }



    }

    public static void main(String[] args)
    {
        Experiment exp = new Experiment("Van Carrier");
        VanCarrierModel model = new VanCarrierModel(null, "Van CarrierModel",
                true, false);
        model.connectToExperiment(exp);
        exp.stop(new SimTime(50000));

        exp.start();

        exp.finish();
        exp.report();

    }
}
