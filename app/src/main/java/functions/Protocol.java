package functions;

/**
 * Created by Yu on 2017/4/10.
 */

public class Protocol {


   public static String readMessage ;

    static char xuliehao = 0;
     public  byte[] Protocol_Sousuo(int MF ,byte CR, byte BW,byte SP){
         byte[] thenew = new byte[14+6];
         thenew[0] = 0x04;
         thenew[1] = 0x11;
         thenew[2] = 0x07;
         thenew[3] = (byte)xuliehao;
         thenew[4] = 14>>4;
         thenew[5] = 14;

         thenew[0+6] = 0x01;
         thenew[4+6] = (byte)MF;//中心频率参数需要确定
         thenew[3+6] = (byte)(MF>>4);
         thenew[2+6] = (byte)(MF>>8);
         thenew[1+6] = (byte)(MF>>12);
         thenew[5+6] = 0x00;
         thenew[6+6] = (byte)(0x00|(1<<7)|(CR<<4)|(6));
         thenew[7+6] = (byte) (0x00|BW);
         if(SP>7){
             thenew[13+6] =20;
         }else{
             thenew[13+6] =14;
          }
         return thenew;
    }

    /**
     *
     * @param bytes
     * @return 十六进制
     */
    public static String bytesToHexString(byte[] bytes) {
        String result = "";
        for (int i = 0; i < bytes.length; i++) {
            String hexString = Integer.toHexString(bytes[i] & 0xFF);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            result += hexString.toUpperCase();
        }
        return result;
    }


}
