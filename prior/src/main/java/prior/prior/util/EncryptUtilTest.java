package prior.prior.util;

public class EncryptUtilTest {
    public static void main(String[] args) {
        String pw="123456";
        System.out.println("密码"+EncryptUtil.encryptMD5(pw));
    }
}
