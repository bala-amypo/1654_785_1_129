// BundleRuleRepository.java
public interface BundleRuleRepository extends JpaRepository<BundleRule, Long> {
    java.util.List<BundleRule> findByActiveTrue();
}