package com.kidcools.testboot.entity;

import com.kidcools.testboot.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Atlas  implements Serializable {
    /**
     * 1.id
     * 2.图集id
     * 3.cllect_time
     * 4.organization
     * 5.tag
     * 6.character
     * 7.title
     * 8.count
     */
    private Long id;
    private Long atlasId;
    private String title;
    private Integer total;
    /**
     * 收录时间
     */
    private Date collectTime;
    /**
     * 组织
     */
    private List<Orgnization> organizationIds;
    /**
     * 标签
     */
    private List<Tag> tagIds;
    /**
     * 角色
     */
    private List<AtlasCharactor> characters;
}
