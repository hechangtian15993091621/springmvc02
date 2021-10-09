package cn.buaa.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.commons.io.IOUtils;

import java.util.Scanner;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/6/6
 **/
public class NettyClient {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        //客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            //注意客户端使用的不是 ServerBootstrap 而是 Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) // 使用 NioSocketChannel 作为客户端的通道实现
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
//                            channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("?".getBytes())));
                            channel.pipeline().addLast("decoder",new StringDecoder());
                            channel.pipeline().addLast("encoder",new StringEncoder());
                            //加入处理器
                            channel.pipeline().addLast(new NettyClientHandler());

                        }
                    });
//            System.out.println("netty client start");

            //启动客户端去连接服务器端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();

            System.out.println("========="+channelFuture.channel().localAddress()+"=============");
            while (in.hasNext()){
                String next = in.nextLine();
                System.out.println(next);
                channelFuture.channel().writeAndFlush(next);
            }
//            for(int i=0;i<200;i++){
//                channelFuture.channel().writeAndFlush("你好你好你好"+"?");
//            }
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
