package Sem2;

import java.util.ArrayList;
import java.util.List;

public class Market implements QueueBehavoir, MarketBehavoir{
    private List<Actor> actorList = new ArrayList<>();
    @Override
    public void acceptToMarket(Actor actor) {
        System.out.println(actor.getName() +  " arrived to market");
        takeInQueue(actor);
    }

    @Override
    public void releaseFromMarket(List<Actor> actors) {
        for (Actor actor : actors){
            System.out.println(actor.getName() + " left the market");
            actorList.remove(actor);
        }
    }

    @Override
    public void update() {
        takeOrders();
        giveOrders();
        releaseFromQueue();
    }

    @Override
    public void takeInQueue(Actor actor) {
        System.out.println(actor.getName() + " join the queue");
        actorList.add(actor);
    }

    @Override
    public void takeOrders() {
        for (Actor actor : actorList){
            if(!actor.isMakeOrder){
                System.out.println(actor.getName() + " make order");
                actor.setMakeOrder(true);
            }
        }
    }

    @Override
    public void giveOrders() {
        for (Actor actor : actorList){
            if(actor.isMakeOrder){
                System.out.println(actor.getName() + " received his order");
                actor.setTakeOrder(true);
            }
        }
    }

    @Override
    public void releaseFromQueue() {
        List<Actor> releasedActors = new ArrayList<>();
        for (Actor actor : actorList){
            if(actor.isTakeOrder){
                System.out.println(actor.getName() + " got out of the queue");
                releasedActors.add(actor);
            }
        }
        releaseFromMarket(releasedActors);
    }
}
