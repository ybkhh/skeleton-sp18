package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> implements Iterable<T>{
    private int first;
            private int last;
    private  T[] rb;
    public ArrayRingBuffer(int capacity){
        rb =(T[]) new Object[capacity];
        first=last=fillCount=0;
        this.capacity=capacity;

    }

    @Override
    public void enqueue(T item){
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        };
       rb[last]=item;
       last=(last+1)%capacity;
       fillCount+=1;
    }
    @Override
    public T dequeue(){
        if(isEmpty()){ throw new RuntimeException("Ring buffer overflow");}
        T val=rb[first];
        first=(first+1)%capacity;
        fillCount--;
       return  val;

    }
    @Override
    public T peek(){return  rb[first];}
 private class ArraryRingBufferIterator implements Iterator<T>{
        private int wiz;
        private  int cur;
        public  ArraryRingBufferIterator(){
            wiz=first;
            cur=0;
            ;
        }
        @Override
     public boolean hasNext(){return cur<fillCount;
        }

     @Override
     public T next() {
       T returnval=rb[wiz];
       wiz=(wiz+1)%capacity;
       cur++;
       return returnval;


     }

 }

    public Iterator<T> iterator(){
        return new ArraryRingBufferIterator();
    }

}
