
public class CollisonBox {
    public int[] origin;
    public int length;
    public int width;
    
    public CollisonBox(int[] orig, int l, int w){
           origin = orig;
           length = l;
           width = w;
    }
    /* DO AFTER I DECIDE CORDINATE SYSTEM AND LEARN ABOUT GRAPHICS CLASS*/
    
    //Takes a another CollisionBox object and checks for collison at this instance  
   
   //Takes a another CollisionBox object and checks for collison at some future position incremented by x and y
    public boolean isColliding(CollisonBox collBox, int x, int y){
        //Check Y
        if(origin[0]+y <= collBox.getOrigin()[0] && origin[0]+length+y >= collBox.getOrigin()[0] || origin[0]+y >= collBox.getOrigin()[0] && origin[0]+y <= collBox.getOrigin()[0]+collBox.length){
        	//Check X
            if(origin[1]+x <= collBox.getOrigin()[1] && origin[1]+width+x >= collBox.getOrigin()[1] || origin[1]+x >= collBox.getOrigin()[1] && origin[1]+x <= collBox.getOrigin()[1]+collBox.width){
            	return true;
            }
            else 
                return false;
        }
        else {
        	return false;
        }
        
    }
    
    public int getLength(){
        return length;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int[] getOrigin(){
        return origin;
    }


}
