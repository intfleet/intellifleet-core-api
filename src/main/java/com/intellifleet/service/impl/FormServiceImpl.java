package com.intellifleet.service.impl;

import com.intellifleet.bean.UserContext;
import com.intellifleet.dto.*;
import com.intellifleet.entity.FormEntity;
import com.intellifleet.exceptions.NoRecordFoundException;
import com.intellifleet.service.FormService;
import com.intellifleet.service.GenericAutowiredService;
import com.intellifleet.service.QueryService;
import com.intellifleet.service.TableService;
import com.intellifleet.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FormServiceImpl extends GenericAutowiredService implements FormService {

//    @Value(value = "external.resource.dir.base.path")
    private String externalResourcesBasePath="external-resources";

    @Autowired
    TableService tableService;

    @Autowired
    protected FormService formService;

    @Autowired
    protected QueryService queryService;

    @Override
    public FormDTO saveOrUpdateForm(FormDTO formDTO) {
        FormEntity formEntity;
        if(formDTO.getId() != null && formDTO.getId() > 0) {
            formEntity = genericRepository.findById(FormEntity.class, formDTO.getId());
            formEntity.setName(formDTO.getName());
            formEntity.setLabel(formDTO.getLabel());
            formEntity.setTableName(formDTO.getTableName());
            formEntity.setNote(formDTO.getNote());
            genericRepository.update(formEntity);
        } else {
            formEntity = mapStructUtils.toFormEntity(formDTO);
            genericRepository.save(formEntity);
        }
        return mapStructUtils.toFormDTO(formEntity);
    }

    @Override
    public FormDTO get(Long id) {
        FormEntity formEntity = genericRepository.findById(FormEntity.class, id);
        if(formEntity == null) {
            throw new NoRecordFoundException();
        }
        return mapStructUtils.toFormDTO(formEntity);
    }

    @Override
    public List<FormListViewDTO> listview(String params) {
        return genericRepository.findAll(FormEntity.class)
                .stream()
                .map(mapStructUtils::toFormListViewDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        genericRepository.deleteById(FormEntity.class, id);
    }

    @Override
    public Map<String, Object> getFormBuilderInfo(Long id) {
        Map<String, Object> rtnResult = new HashMap<>();

        rtnResult.put( "queries", queryService.getQueries());

        FormEntity formEntity = getFormEntity(id);
        if(formEntity != null) {
            rtnResult.put( "fields", tableService.getTableFields(formEntity.getTableName()));

            FormBuilderInfoDTO form = getFormBuilderInfoDTO(formEntity.getId());
            if(form != null) {
                rtnResult.put("form", form);
            }
        }
        return rtnResult;
    }

    @Override
    public FormBuilderInfoDTO saveOrUpdateFormBuilderInfo(Long id, FormBuilderInfoDTO formRequestParamDTO) {

        Path path = FileUtils.getFilePathByType("form");
        if(path != null) {
            String fileName = id+"-form.json";
            path = Paths.get(path.toString(), fileName);

            String content = Utils.stringifyJSON(formRequestParamDTO);
            FileUtils.createFile(path.toString(), content);
        }
        return getFormBuilderInfoDTO(id);
    }

    @Override
    public FormBuilderInfoDTO getFormInfo(Long id) {
        UserContext userContext = Utils.getUserContext();
        FormEntity formEntity = getFormEntity(id);
        if(formEntity == null) {
            throw new NoRecordFoundException();
        }

        return getFormBuilderInfoDTO(formEntity.getId());
    }

    @Override
    public List<FormFieldDTO> getFormFields(Long formId) {
        FormBuilderInfoDTO form = getFormBuilderInfoDTO(formId);
        return form.getGroups().stream()
                .map(FormFieldGroupDTO::getFields)
                .flatMap(List::stream)
                .toList();
    }

    @Override
    public FormEntity getFormEntity(String object) {
        return genericRepository.findAll(FormEntity.class, "object = ?1", List.of(object))
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public FormEntity getFormEntity(Long id) {
        FormEntity formEntity = genericRepository.findById(FormEntity.class, id);
        if(formEntity == null) {
            throw new NoRecordFoundException();
        }
        return formEntity;
    }


    @Override
    public FormBuilderInfoDTO getFormBuilderInfoDTO(Long id) {
        Path path = FileUtils.getFilePathByType("form");
        if(path != null) {
            String fileName = id + "-form.json";
            path = Paths.get(path.toString(), fileName);
            String content = FileUtils.readFile(path);
            return Utils.parseJSON(content, FormBuilderInfoDTO.class);
        }
        return null;
    }

    public List<FormDTO> getFormList() {
        Path path = FileUtils.getFilePathByType("form");
        if(path != null) {
            path = Paths.get(path.toString(), "forms.json");
            String content = FileUtils.readFile(path);
            return Utils.parseArrayJSON(content, FormDTO.class);
        }
        return List.of();
    }

    @Override
    public String getTableName(Long formId) {
        FormEntity entity = getFormEntity(formId);
        if(entity == null) {
            throw new NoRecordFoundException();
        }
        return entity.getTableName();
    }

    @Override
    public List<FormEntity> getFormListByTableNames(List<String> tableNameList) {
        return genericRepository.findAll(FormEntity.class)
                .stream()
                .filter(f -> tableNameList.contains(f.getTableName()))
                .collect(Collectors.toList());
    }
}
