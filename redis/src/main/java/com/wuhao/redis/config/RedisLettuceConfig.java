package com.wuhao.redis.config;

import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.resource.NettyCustomizer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RedisLettuceConfig {
    @Bean(destroyMethod = "shutdown")
    ClientResources clientResources() {
        //检查连接是否空闲
        NettyCustomizer nettyCustomizer = new NettyCustomizer() {
            @Override
            public void afterChannelInitialized(Channel channel) {
                //表示10s没有写入就主动关闭连接
                channel.pipeline().addLast(
                        new IdleStateHandler(0, 10, 0));
                channel.pipeline().addLast(new ChannelDuplexHandler() {
                    @Override
                    public void userEventTriggered(ChannelHandlerContext ctx,
                                                   Object evt) throws Exception {
                        if (evt instanceof IdleStateEvent) {
                            log.info("进入 IdleStateEvent");
                            IdleStateEvent event = (IdleStateEvent) evt;
                            if (event.state() == IdleState.WRITER_IDLE) {
                                log.info("关闭连接");
                                ctx.close();
                            }
                        }
                    }
                });
            }

            @Override
            public void afterBootstrapInitialized(Bootstrap bootstrap) {
            }
        };
        DefaultClientResources.Builder builder =
                DefaultClientResources.builder();
        builder.nettyCustomizer(nettyCustomizer);
        return builder.build();
    }
}