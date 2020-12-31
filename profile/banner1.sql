-- ----------------------------
--  Table structure for `banner1`
-- ----------------------------
DROP TABLE IF EXISTS `banner1`;
CREATE TABLE `banner1` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部分banner1可能有标题图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
--  Records of `banner1`
-- ----------------------------
BEGIN;
INSERT INTO `banner1` VALUES ('1', 'b-1', '首页顶部主banner1', '2019-07-28 04:47:15.000', '2019-08-04 01:03:16.000', null, null, null), ('2', 'b-2', '热销商品banner1', '2019-08-01 00:37:47.000', '2019-09-20 00:56:45.843', null, null, null);
COMMIT;


package com.ljc.shop3.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Where(clause = "delete_time is null and online=1")
public class GridCategory extends BaseEntity{
    @Id
    private Long id;

    private String title;

    private String name;

    private Long categoryId;

    private Long rootCategoryId;

}
