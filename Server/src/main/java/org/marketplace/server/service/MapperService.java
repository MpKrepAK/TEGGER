package org.marketplace.server.service;

import org.jetbrains.annotations.NotNull;
import org.marketplace.server.model.dto.*;
import org.marketplace.server.model.entity.*;
import org.marketplace.server.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MapperService {
    private final UserRepository userRepository;

    public MapperService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User userFromAuthDTO(@NotNull AuthDTO authDTO){
        return userRepository
                .findUserByLoginAndPassword(authDTO.getLogin(), authDTO.getPassword())
                .orElseThrow(()->new NoSuchElementException("Пользователь не найден"));
    }

    public CardDTO cardDTOFromProduct(@NotNull Product product){
        return new CardDTO(
                product.getId(),
                product.getType().getName(),
                product.getManufacturer().getName(),
                product.getCount(),
                product.getCost(),
                product.getRating(),
                product.getMainImage(),
                product.getTitle()
        );
    }

    public CommentDTO commentDTOFromComment(@NotNull Comment comment){
        return new CommentDTO(
                comment.getUser().getFirstName(),
                comment.getCommentStar(),
                comment.getMessage()
        );
    }

    public ComplaintDTO complaintDTOFromComplaint(@NotNull Complaint complaint){
        return new ComplaintDTO(
                complaint.getId(),
                complaintTypeDTOFromComplaintTyp(complaint.getType()),
                complaint.getMessage(),
                complaint.getProduct().getId()
        );
    }

    public OneFieldEntityDTO complaintTypeDTOFromComplaintTyp(@NotNull ComplaintType type){
        return new OneFieldEntityDTO(type.getId(), type.getName());
    }

    public ProductDTO productDTOFromProduct(@NotNull Product product){
        return new ProductDTO(
                product.getId(),
                product.getType().getName(),
                product.getManufacturer().getName(),
                product.getCount(),
                product.getRating(),
                product.getMainImage(),
                product.getTitle(),
                product.getInfo(),
                product.getImages().stream().map(Image::getValue).collect(Collectors.toSet()),
                product.getInfoBooleans()
                        .stream()
                        .map(this::productInfoBooleanDTOFromProductInfoBoolean)
                        .collect(Collectors.toSet()),
                product.getInfoNumbers()
                        .stream()
                        .map(this::productInfoNumberDTOFromProductInfoNumber)
                        .collect(Collectors.toSet()),
                product.getInfoStrings()
                        .stream()
                        .map(this::productInfoStringDTOFromProductInfoString)
                        .collect(Collectors.toSet())
        );
    }

    public ProductInfoBooleanDTO productInfoBooleanDTOFromProductInfoBoolean(
            @NotNull ProductInfoBoolean info){
        return new ProductInfoBooleanDTO(
                info.getId(),
                info.getInfo().getName(),
                info.getValue()
        );
    }

    public ProductInfoNumberDTO productInfoNumberDTOFromProductInfoNumber(
            @NotNull ProductInfoNumber info){
        return new ProductInfoNumberDTO(
                info.getId(),
                info.getInfo().getName(),
                info.getValue()
        );
    }

    public ProductInfoStringDTO productInfoStringDTOFromProductInfoString(
            @NotNull ProductInfoString info){
        return new ProductInfoStringDTO(
                info.getId(),
                info.getInfo().getName(),
                info.getValue()
        );
    }

    public OneFieldEntityDTO fromProductTag(@NotNull ProductTag tag){
        return new OneFieldEntityDTO(tag.getId(), tag.getName());
    }


    public ProductTag productTagFromDTO(@NotNull OneFieldEntityDTO productTagDTO) {
        ProductTag productTag = new ProductTag();
        productTag.setName(productTagDTO.getName());
        return productTag;
    }

    public OneFieldEntityDTO fromInfoString(InfoString infoString) {
        return new OneFieldEntityDTO(infoString.getId(), infoString.getName());
    }

    public InfoString infoStringFromDTO(OneFieldEntityDTO dto) {
        return new InfoString(
                dto.getId(),
                dto.getName()
        );
    }

    public OneFieldEntityDTO fromInfoNumber(InfoNumber info) {
        return new OneFieldEntityDTO(info.getId(), info.getName());
    }

    public InfoNumber infoNumberFromDTO(OneFieldEntityDTO dto) {
        return new InfoNumber(dto.getId(),dto.getName());
    }

    public InfoBoolean infoBooleanFromDTO(OneFieldEntityDTO dto) {
        return new InfoBoolean(dto.getId(),dto.getName());
    }

    public OneFieldEntityDTO fromInfoBoolean(InfoBoolean info) {
        return new OneFieldEntityDTO(info.getId(), info.getName());
    }

    public ProductType productTypeFromDTO(OneFieldEntityDTO dto) {
        return new ProductType(dto.getId(), dto.getName());
    }

    public OneFieldEntityDTO fromProductType(ProductType productType) {
        return new OneFieldEntityDTO(productType.getId(), productType.getName());
    }

    public OneFieldEntityDTO fromComplaintType(ComplaintType complaintType) {
        return new OneFieldEntityDTO(complaintType.getId(), complaintType.getName());
    }

    public ComplaintType complaintTypeFromDTO(OneFieldEntityDTO dto) {
        return new ComplaintType(dto.getId(), dto.getName());
    }

    public OneFieldEntityDTO fromManufacturer(Manufacturer manufacturer) {
        return new OneFieldEntityDTO(manufacturer.getId(), manufacturer.getName());
    }

    public Manufacturer manufacturerFromDTO(OneFieldEntityDTO dto) {
        return new Manufacturer(dto.getId(), dto.getName());
    }
}
