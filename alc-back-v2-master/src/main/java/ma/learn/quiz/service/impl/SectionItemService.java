package ma.learn.quiz.service.impl;

import ma.learn.quiz.bean.Section;
import ma.learn.quiz.bean.SectionItem;
import ma.learn.quiz.dao.SectionDao;
import ma.learn.quiz.dao.SectionItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionItemService {

    @Autowired
    private SectionItemDao sectionItemDao;
    @Autowired
    private SectionDao sectionDao;

    public int create(List<SectionItem> sectionItems, Long idSection) {
        if (idSection == null) {
            return -3;
        } else {
            Section section = sectionDao.findSectionById(idSection);
            if (section == null) {
                return -1;
            } else {
                if (sectionItems.isEmpty()) {
                    return -2;
                } else {
                    for (SectionItem item:sectionItems) {
                        item.setSection(section);
                        sectionItemDao.save(item);
                    }
                    return 1;
                }
            }
        }
    }

    public int update(List<SectionItem> sectionItems) {
        if (sectionItems.isEmpty()) {
            return -2;
        } else {
            for (SectionItem item:sectionItems) {
                if (sectionItemDao.findSectionItemById(item.getId()) == null) {
                    return -1;
                } else {
                    sectionItemDao.save(item);
                }
            }
            return 1;
        }
    }

    public int delete(Long sectionItemId) {
        SectionItem sectionItem = sectionItemDao.findSectionItemById(sectionItemId);

        if (sectionItem == null) {
            return -1;
        } else {
            System.out.println(sectionItem);
            sectionItemDao.delete(sectionItem);
            return 1;
        }
    }

    public int deleteMultiple(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return -1;
        } else {
            for (Long id : ids) {
                SectionItem sectionItem = sectionItemDao.findSectionItemById(id);
                if (sectionItem != null) {
                    sectionItemDao.delete(sectionItem);
                }
            }
            return 1;

        }

    }

    public List<SectionItem> findAllSectionItemsBySection(Long sectionId) {
        if (sectionId == null) {
            return null;
        } else {
            Section section = sectionDao.findSectionById(sectionId);
            if (section == null) {
                return null;
            } else {
                return section.getSectionItems();
            }
        }
    }

}
