public class MpSoc{
    Processor [][] processors = new Processor[3][9];
    CentralProcessor cProcessor;

    MpSoc(){
        int id=0;

        cProcessor = new CentralProcessor(processors);

        for(int x=0;x<processors.length;x++){
            for(int y=0;y<processors[x].length;y++){
                processors[x][y] = new Processor(id++,cProcessor); 
            }      
        }

        cProcessor.start();

        for(int x=0;x<processors.length;x++){
            for(int y=0;y<processors[x].length;y++){
                processors[x][y].start(); 
            }      
        }
        // cProcessor.start();
    }
     
}