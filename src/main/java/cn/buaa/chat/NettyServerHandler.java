package cn.buaa.chat;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/6/6
 **/

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义Handler需要继承netty规定好的某个HandlerAdapter(规范)
 */
public class NettyServerHandler extends SimpleChannelInboundHandler {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress()+"上线了"+simpleDateFormat.format(new Date())+"\n");
        channelGroup.add(channel);
        System.out.println(channel.remoteAddress()+"上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress()+"下线了"+simpleDateFormat.format(new Date())+"\n");
        channelGroup.add(channel);
        System.out.println(channel.remoteAddress()+"下线了");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        Channel channel = channelHandlerContext.channel();
        for(Channel ctx:channelGroup){
            if(ctx!=channel){
                ctx.writeAndFlush("[客户端]"+ctx.remoteAddress()+"发送了消息:"+o.toString()+"\n");
            }else{
                ctx.writeAndFlush("[自己]发送了消息:"+o.toString()+"\n");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        try {

        } catch (Exception e) {

        }
    }
}
