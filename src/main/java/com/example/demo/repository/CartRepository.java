public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
