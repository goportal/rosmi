class Draw{

    int [] posx = { 50,125,225 ,25,150,-1   ,70,160,150  ,20,100,225 ,155,150,-1 ,70,120,150 ,35,200,150 ,175,-100,30 ,50,-450,140 };  //posx        
    int [] posy = { 125,50,200 ,50,50,200   ,80,20,250   ,20,175,-1  ,15,170,-1  ,60,75,-1   ,175,25,150 ,160,25,80   ,70,25,175   };  //posy        
    int [] tamx = { 75,75,100  ,100,100,25  ,150,140,200 ,90,100,100 ,130,90,25  ,20,150,200 ,50,700,100 ,80,700,70   ,40,700,50  };  //tamx        
    int [] tamy = { 75,75,100  ,100,100,100 ,65,40,150   ,90,100,250 ,85,110,250 ,25,150,50  ,30,40,100  ,80,40,90    ,130,40,50   };  //tamy         

    public int[] get(int aux){
        int [] ret = {posx[aux],posy[aux],tamx[aux],tamy[aux]};
        // System.out.println("count: "+aux);
        return ret;
    }

}