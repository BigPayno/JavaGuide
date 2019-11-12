package utils.sftp;

import com.google.common.base.Splitter;
import com.jcraft.jsch.*;
import com.sun.deploy.net.JARSigningException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;

/**
 * @author payno
 * @date 2019/10/29 09:31
 * @description
 */
@Slf4j
@Data
public class SftpClient implements AutoCloseable{
    private SftpClient(){
    }
    private ChannelSftp sftp=null;
    private Session session;
    private String localDir="/";
    public static SftpClient login(String ip,int port,String name,String pass){
        SftpClient sftpClient=new SftpClient();
        try{
            JSch jSch=new JSch();
            sftpClient.session=jSch.getSession(name,ip,port);
            sftpClient.session.setPassword(pass);
            sftpClient.session.setTimeout(10000);
            sftpClient.session.setConfig("StrictHostKeyChecking","no");
            sftpClient.session.connect();
            Channel channel=sftpClient.session.openChannel("sftp");
            channel.connect();
            if(channel instanceof ChannelSftp){
                sftpClient.sftp=(ChannelSftp) channel;
            }else{
                throw new IllegalStateException();
            }
        }catch (JSchException | IllegalStateException e){
            log.error("",e);
        }
        return sftpClient;
    }

    @Override
    public void close() throws Exception {
        if(sftp!=null){
            if(sftp.isConnected()){
                sftp.disconnect();
            }
        }
        if(session!=null){
            if(session.isConnected()){
                session.disconnect();
            }
        }
    }

    public SftpClient cd(String dir){
        try{
            localDir=dir;
            sftp.cd(localDir);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    public SftpClient uploadFile(String sftpFileName, InputStream input){
        try{
            sftp.cd(localDir);
            sftp.put(input,sftpFileName,ChannelSftp.OVERWRITE);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    public SftpClient rmIf(Predicate<String> condition){
        try{
            Vector<?> cmds=sftp.ls(localDir);
            cmds.stream().map(v->((Object) v).toString()).map(cmd->{
                List<String> words= Splitter.on(" ").splitToList(cmd);
                return words.get(words.size()-1);
            }).forEach(fileName->{
                if(condition.test(fileName)){
                    try{
                        sftp.rm(fileName);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }
}
