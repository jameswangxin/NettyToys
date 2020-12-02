package com.chipizz.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Scanner;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + " 客户端准备开始写数据");

        //获取数据
        ByteBuf buffer = getByteBuffer(ctx);
        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuffer(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();

        byte[] bytes = "晚上好，你吃饭了吗".getBytes(Charset.forName("utf-8"));

        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println(new Date() + ": 客户端读到的数据-> " + byteBuf.toString(Charset.forName("utf-8")));
    }
}
