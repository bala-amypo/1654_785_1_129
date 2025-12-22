// CartItemRepository.java
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    java.util.List<CartItem> findByCartId(Long cartId);
}