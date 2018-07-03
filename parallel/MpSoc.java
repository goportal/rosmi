public class MpSoc{
    Processor [] processors = new Processor[9];
    CentralProcessor cProcessor;

    MpSoc(){
        cProcessor = new CentralProcessor(processors);
        for(int I=0;I<processors.length;I++){
            processors[I] = new Processor(I,cProcessor);       
        }
        cProcessor.start();
        for(int I=0;I<processors.length;I++){
            processors[I].start();
        }
        // cProcessor.start();
    }
     
}