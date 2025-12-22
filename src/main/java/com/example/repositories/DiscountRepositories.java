// DiscountApplicationRepository.java
public interface DiscountApplicationRepository extends JpaRepository<DiscountApplication, Long> {
    java.util.List<DiscountApplication> findByCartId(Long cartId);
}