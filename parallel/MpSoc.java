public class MpSoc{
    Processor [] processors = new Processor[9];
    CentralProcessor cProcessor;

    MpSoc(){
        for(int I=0;I<processors.length;I++){
            processors[I] = new Processor(I);
        }
        cProcessor = new CentralProcessor(processors);
        cProcessor.run();
    }
     
}