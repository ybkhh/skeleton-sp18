

public class ArrayDeque <T>{
private T[] array;
private  int size;
private  int length;
private  int head;
private  int tail;

public  ArrayDeque(){
    T[] arrary = (T[]) new Object[8];

    this.size=0;
    length=8;
    head=4;
    tail=4;

}
 public boolean isEmpty(){
    return size==0;
 }
public int size (){
    return  size;
}
 public int minusOne(int index){
    index=(index-1+length)%length;
    return index;
 }
 public int PlusOne(int index ){
    index=(index+1+length)%length;
    return  index;
 }//用来更新head ,tail 的下标，避免代码重复

    public void addFirst(T item){
    if (size<length){


    head=minusOne(head);
    array [head]=item;
    size++;}

}
 public void addLast(T item){
    if (size<length){
    array[tail]=item;
    tail=PlusOne(tail);
    size++;}

 }
 public T removeFirst(){
     T val=array[head];
    // array[head]=null;
     head=  PlusOne(head);
     size--;
     return val ;





 }
 public  T removeLast(){
     T val=array[minusOne(tail)];
     //array[minusOne(tail)]=null;并不需要user不关心底层实现
     tail=minusOne(tail);
     size--;
     return  val;
 }
    public T get(int index){
        if (index >= size) {
            return null;
        }
       int ptr=head;
        for (int i=0;i<index;i++){
            ptr=PlusOne(ptr
            );
        }
        return array[ptr];

}

    public void printDeque(){
        for (int i=head;i<minusOne(tail);i++){
            System.out.print(array[i]+" ");
        }

    }

    


}
