package org.example.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 存储用户发送的消息
 * @TableName user_messages
 */
@TableName(value ="user_messages")
@Data
public class UserMessages implements Serializable {
    /**
     * 发送消息的用户ID（手动维护关系）
     */

    @TableId(type = IdType.AUTO)
    private Long userId;
    /**
     * 用户消息唯一标识
     */
    private int userMessageId;

    /**
     * 关联的会话ID（手动维护关系）
     */
    private String sessionId;



    /**
     * 消息内容
     */
    private String messageText;

    /**
     * 消息发送时间
     */
    private Date createdTime;

    /**
     * 附加信息（如情感分析结果、实体识别结果等）
     */
    private Object metadata;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}