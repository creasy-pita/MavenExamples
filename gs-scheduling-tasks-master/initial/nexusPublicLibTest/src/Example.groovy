
public class Example{
    static  void main(String[] args){
        print("ooooo");
        String ss = "gisqshoppingcart/-/gisqshoppingcart-0.1.2.tgz";
        String[] nameList = ss.split('/');
        String componentName = nameList[0]
        String version =nameList[2].replace(componentName+"-","");
        version = version.replace(".tgz","")//TBD 去除末尾的文件扩展名
        print(version)
    }
}
