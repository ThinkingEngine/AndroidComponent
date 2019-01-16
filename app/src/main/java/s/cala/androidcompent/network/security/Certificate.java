package s.cala.androidcompent.network.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * package name:s.cala.androidcompent.network.security
 * create:cala
 * date:2019/1/16
 * commits:证书
 */
public class Certificate {

    /**
     * 通过okhttpClient来设置证书
     *
     * @param builder okhttpClient.builder
     * @param certificates 读取的证书流文件
     * */
    public static void setCertificates(OkHttpClient.Builder builder, InputStream... certificates){
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for(InputStream certificate:certificates){
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias,certificateFactory.generateCertificate(certificate));
                try {
                    if(certificate != null){
                        certificate.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if(trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)){
                throw new IllegalStateException("Unexpected default trust manager:"+Arrays.toString(trustManagers));
            }

            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,trustManagerFactory.getTrustManagers(),new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory,trustManager);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
