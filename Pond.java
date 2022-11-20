import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
public class Pond implements Runnable{
        ArrayList<String> f;
        ReentrantLock lock;
        
        public Pond(ArrayList<String> f, ReentrantLock lock){
            this.f = f;
            this.lock = lock;
        }
        
        @Override
        public void run(){
            Random rand = new Random();
            while(true){
                if(lock.tryLock()){
                    try{
                        if(f.isEmpty()){
                            break;
                        } else{
                            int index1 = rand.nextInt(f.size());
                            String f1 = f.get(index1);
                            
                            int index2 = rand.nextInt(f.size());
                            String f2 = f.get(index2);
                            
                            if(f1 == "m" && f2 == "m"){
                                f.remove(index1);
                                f.remove(index2);
                            } else if(f1 == "f" && f2 == "f"){
                                f.remove(index1);
                            } else {
                                index1 = rand.nextInt(f.size());
                                index2 = rand.nextInt(f.size());
                                f.add(f.get(index1));
                                f.add(f.get(index2));
                            }
                            System.out.println("The fishes left in the pond are:");
                            System.out.println(f);
                        }
                    }
                    finally{
                        lock.unlock();
                    }
                }
            }
        }
    }
    
