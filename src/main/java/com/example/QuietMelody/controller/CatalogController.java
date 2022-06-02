package com.example.QuietMelody.controller;

        import com.example.QuietMelody.domain.*;
        import com.example.QuietMelody.repos.CartRepo;
        import com.example.QuietMelody.repos.CatalogRepo;
        import com.example.QuietMelody.repos.OrderListRepo;
        import com.example.QuietMelody.service.CartService;
        import com.example.QuietMelody.service.CatalogService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.security.core.annotation.AuthenticationPrincipal;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;


        import java.security.Principal;
        import java.util.*;
        import java.text.SimpleDateFormat;

        import static com.example.QuietMelody.domain.Status.INPROCESSING;

/*!
    \brief Контроллер, используемый для перехода между страницами
    \param @GetMapping() Указывает путь для перехода на страницу
    \return Главная страница веб-приложения
    \return Страница с ошибкой
 */

@RequestMapping("/catalog")
@Controller
public class CatalogController {
    @Autowired
    private CatalogRepo catalogRepo;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private OrderListRepo orderListRepo;

    @GetMapping
    public String catalog(Model model, @RequestParam(defaultValue = "0") int page)
    {
        model.addAttribute("products", catalogRepo.findAvailable(PageRequest.of(page,5)));
        model.addAttribute("currentPage",page);
        return "catalog";
    }
    @GetMapping("/ascendingprice")
    public String ascendingprice(Model model, @RequestParam(defaultValue = "0") int page)
    {

        model.addAttribute("products", catalogRepo.sortAscending(PageRequest.of(page,5)));
        model.addAttribute("currentPage",page);
        return "catalog";
    }
    @GetMapping("/descendingprice")
    public String descendingprice(Model model, @RequestParam(defaultValue = "0") int page)
    {

        model.addAttribute("products", catalogRepo.sortDescending(PageRequest.of(page,5)));
        model.addAttribute("currentPage",page);
        return "catalog";
    }
    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addItemToCatalog(Model model)
    {
        model.addAttribute("catalog", new Catalog());
        return "addItem";
    }
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addItem(@ModelAttribute("catalog") Catalog item) /// Метод для создания нового пользователя в базе данных
    {
        catalogRepo.save(item);
        return "redirect:/catalog";
    }

    @GetMapping("{product_id}")
    public String addItemToCart(@AuthenticationPrincipal User user, @PathVariable long product_id) {
        Long user_id = user.getId();
        Cart newCart = new Cart();
        newCart.setProduct_id(product_id);
        newCart.setQuantity(1);
        newCart.setUser_id(user_id);
        if (cartService.findCartByIds(user_id, product_id).isEmpty()) {
            newCart.setPrice(catalogRepo.getById(product_id).getPrice());
            cartService.save(newCart);
        } else {
            List<Cart> cartToUpdate = cartService.findCartByIds(user_id, product_id);
            Cart cart1 = cartToUpdate.get(0);
            cart1.setQuantity(cart1.getQuantity() +  newCart.getQuantity());
            cart1.setPrice(catalogRepo.getById(product_id).getPrice() * cart1.getQuantity());
            cartService.save(cart1);
        }
        return "redirect:/catalog";
    }

    @GetMapping("/cart")
    public String Cart(Model model, @AuthenticationPrincipal User user){
        List<Cart> allProducts = new ArrayList<>();
        Long user_id = user.getId();
        cartService.findAll().forEach(i -> {
            if (i.getUser_id() == user_id) {
                Catalog product = catalogRepo.getOne(i.getProduct_id());
                allProducts.add(new Cart(i.getId(),user_id,product.getId(),i.getQuantity(),
                        product.getName(),i.getPrice(), catalogRepo.getOne(i.getProduct_id()).getImgUrl()));
            }
        });
        model.addAttribute("cart",allProducts);
        return "cart";
    }
    @GetMapping("/cart/delete/{id}")
    public String deleteItemFromCart(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User user) {
        List<Cart> allProducts = new ArrayList<>();
        Long user_id = user.getId();
        Cart cartById = cartRepo.getOne(id);
        if (user_id == cartById.getUser_id()) {
            if (cartById.getQuantity() > 1) {
                cartById.setQuantity(cartById.getQuantity() - 1);
                cartService.save(cartById);
            }
            else{
                cartService.deleteById(id);
            }
        }
        cartService.findAll().forEach(i -> {
            if (i.getUser_id() == user_id) {
                allProducts.add(new Cart(i.getId(),user_id,
                        catalogRepo.getOne(i.getProduct_id()).getId(),i.getQuantity(),
                        catalogRepo.getOne(i.getProduct_id()).getName(),i.getPrice(), catalogRepo.getOne(i.getProduct_id()).getImgUrl()));
            }
        });
        model.addAttribute("cart",allProducts);
        return "cart";
    }
    @GetMapping("/cart/clean")
    public String cleanCart(@AuthenticationPrincipal User user) {
        List<Cart> cleanList = cartRepo.findAll();
        for(Cart cart : cleanList){
            if(cart.getUser_id() == user.getId()){
                cartRepo.deleteById(cart.getId());
            }
        }
        return "cart";
    }
    @GetMapping("/cart/placeOrder")
    public String placeOrder(@AuthenticationPrincipal User user) {
        List<Cart> cleanList = cartRepo.findAll();
        OrderList order = new OrderList();
        String idOfProductsbuf = "";
        order.setStatus(INPROCESSING);
        order.setAuthor(user);
        SimpleDateFormat formatertime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat formaterdate = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        order.setTime(formatertime.format((date)));
        order.setDate(formaterdate.format((date)));
        for(Cart cart : cleanList){
            if(cart.getUser_id() == user.getId()){
                idOfProductsbuf += cart.getProduct_id();
                idOfProductsbuf += "|";
                order.setIdOfProducts(idOfProductsbuf);
                cartRepo.deleteById(cart.getId());
            }
        }
        orderListRepo.save(order);
        return "payment";
    }
}