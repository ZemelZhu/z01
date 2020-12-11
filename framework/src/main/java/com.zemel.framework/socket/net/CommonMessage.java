package com.zemel.framework.socket.net;

import java.io.Serializable;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonMessage {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonMessage.class);

    /**
     * 包头长度
     */
    public static final short HDR_SIZE = 8;

    /**
     * 包分隔符
     */
    public static final short HEADER = 0x71ab;
    // private short header = HEADER; // 包头
    // /** 数据包长度，包括包头和包体。 */
    // private short len;
    /**
     * 校验和
     */
    private short checksum;
    /**
     * 协议号
     */
    private short code;
    /**
     * 包体数据
     */
    private byte[] bodyData;

    /**
     * build实例用，防止类外部创建空消息。
     */
    private CommonMessage() {
    }

    /**
     * 构造方法
     *
     * @param code 协议号
     */
    public CommonMessage(short code) {
        this.code = code;
    }

    /**
     * 构建实例。<br>
     * 注意：所构建的实例的校验和是从输入参数msgData中读取的，并非通过消息数据计算所得。
     *
     * @param msgData 消息数据，至少包括包头，允许不带包体数据。
     * @return
     */
    public static CommonMessage build(byte[] msgData, int length) {
        CommonMessage builder = new CommonMessage();
        if (msgData == null || length < HDR_SIZE || length > Short.MAX_VALUE) {
            return null;
        }

        ByteBuffer buff = ByteBuffer.wrap(msgData);
        // 跳过分隔符和包长，包长由输入参数长度确定。
        buff.position(4);
        builder.checksum = buff.getShort();
        builder.code = buff.getShort();
        int bodyLen = length - HDR_SIZE;
        if (bodyLen > 0) {
            builder.bodyData = new byte[bodyLen];
            buff.get(builder.bodyData, 0, bodyLen);

            // 检查校验和是否正确
            short getCS = builder.calcChecksum(msgData, length);
            if (builder.checksum != getCS) {
                LOGGER.warn("数据包校验失败，数据包将被丢弃。code: 0x{}。校验和应为{}，实际接收校验和为{}",
                        builder.getCode(), getCS, builder.checksum);
                return null;
            }
        } else {
            builder.bodyData = null;
        }

        return builder;
    }

    /**
     * 数据包转换为ByteBuffer，包括包头和包体。
     *
     * @return
     */
    public ByteBuffer toByteBuffer() {
        short len = getLen();
        ByteBuffer buff = ByteBuffer.allocate(len);
        buff.putShort(HEADER);
        // 插入长度
        buff.putShort(len);
        // 先跳过校验和
        buff.position(6);
        buff.putShort(code);
        if (bodyData != null) {
            buff.put(bodyData);
        }
        int pos = buff.position();

        // 插入校验和
        buff.position(4);
        buff.putShort(calcChecksum(buff.array(), len));

        buff.position(pos);

        buff.flip();
        return buff;
    }

    /**
     * 获取数据包的长度，包括包头和包体。
     *
     * @return
     */
    public short getLen() {
        short bodyLen = bodyData == null ? 0 : (short) bodyData.length;
        return (short) (HDR_SIZE + bodyLen);
    }

    /**
     * 获取校验和。<br>
     * 注意：获取到的校验和可能与实时计算的不相等，这取决于您的操作顺序。
     *
     * @return
     */
    public short getChecksum() {
        return checksum;
    }

    /**
     * 获取协议号
     *
     * @return
     */
    public short getCode() {
        return code;
    }

    /**
     * 设置协议号
     *
     * @param code
     */
    public void setCode(short code) {
        this.code = code;
    }

    /**
     * 获取包体，包体允许为null。
     *
     * @return
     */
    public byte[] getBody() {
        return bodyData;
    }

    /**
     * 设置包体，包体允许为null。
     *
     * @param bytes
     */
    public void setBody(byte[] bytes) {
        this.bodyData = bytes;
    }

    /**
     * 计算校验和
     *
     * @param data 完整的消息数据，包括包头和包体，计算将从第7个字节开始。
     * @return
     */
    private short calcChecksum(byte[] data, int length) {
        int val = 0x77;
        int i = 6;
        // int size = data.length;
        while (i < length) {
            val += (data[i++] & 0xFF);
        }
        return (short) (val & 0x7F7F);
    }

    /**
     * 包头的字符串表示形式。
     *
     * @return
     */
    public String headerToStr() {
        StringBuilder sb = new StringBuilder();
        sb.append("len: ").append(getLen());
        sb.append(", checksum: ").append(checksum);
        sb.append(", code: 0x").append(Integer.toHexString(code));
        // sb.append(", playerId: ").append(playerId);
        // sb.append(", empty1: ").append(empty1);
        // sb.append(", empty2: ").append(empty2);
        return sb.toString();
    }

    /**
     * 数据包的字符串表示形式。
     *
     * @return
     */
    public String detailToStr() {
        String str = "";
        if (bodyData != null) {
            try {
                str = new String(bodyData, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                str = "(UnsupportedEncodingException)";
            }
        }
        return String.format("%s. content:%s.", headerToStr(), str);
    }
}
