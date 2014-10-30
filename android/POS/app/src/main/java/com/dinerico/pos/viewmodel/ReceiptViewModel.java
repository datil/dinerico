package com.dinerico.pos.viewmodel;

import com.dinerico.pos.db.InvoiceDB;
import com.dinerico.pos.exception.ValidationError;
import com.dinerico.pos.model.Account;
import com.dinerico.pos.model.Address;
import com.dinerico.pos.model.Customer;
import com.dinerico.pos.model.EmailAccount;
import com.dinerico.pos.model.EmailBody;
import com.dinerico.pos.model.EmailMessage;
import com.dinerico.pos.model.EmailResponse;
import com.dinerico.pos.model.Invoice;
import com.dinerico.pos.model.InvoiceItem;
import com.dinerico.pos.model.InvoiceResponse;
import com.dinerico.pos.model.Order;
import com.dinerico.pos.model.OrderItem;
import com.dinerico.pos.model.Product;
import com.dinerico.pos.model.SigningInvoice;
import com.dinerico.pos.model.Store;
import com.dinerico.pos.model.Tag;
import com.dinerico.pos.network.service.EmailService;
import com.dinerico.pos.network.service.InvoiceService;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by josephleon on 10/10/14.
 */
public class ReceiptViewModel {

  private String email;
  private String names;
  private String address;
  private String telephoneNumber;
  private String customerId;
  private boolean finalConsumer;
  private String idType;

  private Invoice invoice;
  private Customer customer;

  private InvoiceService invoiceService;
  private EmailService emailService;

  private InvoiceDB invoiceDB;

  private static final String GUIA_REMISION = "";
  private static final String PROPINA = "0.00";
  private static final String COD_AUXILIAR_ITEM = "";
  private static final String DESCUENTO = "0.00";
  private static final String COD_ICE = "";
  private static String key = "497eacaa70f34afb86e343d568540cc5";

  public ReceiptViewModel(Invoice invoice, Customer customer,
                          InvoiceService invoiceService,
                          EmailService emailService, InvoiceDB invoiceDB) {
    this.invoiceDB = invoiceDB;
    this.invoice = invoice;
    this.customer = customer;
    this.invoiceService = invoiceService;
    this.emailService = emailService;
  }

  public boolean createInvoiceOnDB() {
    return  Account.getInstance().getStore().getInvoices().add(invoice);
  }

  public boolean validate() throws ValidationError {
    if (finalConsumer)
      return customer.isValidEmail() && customer.isValidIdentificacion() &&
              customer.isValidName();
    else
      return customer.isValidEmail() && customer.isValidIdentificacion() &&
              customer.isValidName() && customer.isValidAddress() && customer
              .isValidTelephone();
  }

  public void sendEmail(RequestListener<EmailResponse> listener){
    EmailBody email = new EmailBody();
    email.setKey(EmailBody.KEY);
    email.setTemplateName(EmailBody.TEMPLATE_NAME);

    Tag tag = new Tag("example name","example content");
    email.getTemplateContent().add(tag);

    EmailMessage emailMessage = new EmailMessage();
    EmailAccount emailAccount = new EmailAccount(this.email,names,
            EmailAccount.TYPE);
    emailMessage.setSubject("Recibo: *|RECIBO|* de Datilmedia");
    emailMessage.getTo().add(emailAccount);
    emailMessage.setBccAddress("juanantonioplaza@datilmedia.com");
    emailMessage.setMerge(true);
    emailMessage.setMergeLanguage("mailchimp");
    Tag tag1 = new Tag("name", Account.getInstance().getStore()
            .getNombreComercial());
    Tag tag2 = new Tag("id", this.customerId);
    Tag tag3 = new Tag("direccion", address);
    Tag tag4 = new Tag("recibo", "001-0234");
    emailMessage.getGlobalMergeVars().add(tag1);
    emailMessage.getGlobalMergeVars().add(tag2);
    emailMessage.getGlobalMergeVars().add(tag3);
    emailMessage.getGlobalMergeVars().add(tag4);
    email.setEmailMessage(emailMessage);
    emailService.send(email, listener);

  }

  public void createInvoiceOnSRI(String claveFacturacionEletronica,
                                 String codigoEstablecimiento,
                                 String sequential,
                                 Order order,
                                 RequestListener<InvoiceResponse> listener) {

    invoice.setApikey(claveFacturacionEletronica);
    invoice.setCodigoestablecimiento(codigoEstablecimiento);
    invoice.setCodigopuntoventa(Store.COD_PUNTO_VENTA);
    invoice.setSecuencia(sequential);
    invoice.setGuiaremision(GUIA_REMISION);
    invoice.setCliente(customer);
    invoice.setPropina(PROPINA);
    invoice.setAdicionales(new ArrayList<String>());

    ArrayList<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();
    for (OrderItem item : order.getItems()) {
      Product product = item.getProduct();
      InvoiceItem invoiceItem = new InvoiceItem();
      invoiceItem.setCantidad(item.getAmount());
      invoiceItem.setCodigoprincipal(product.getId() + "");
      invoiceItem.setCodigoauxiliar(COD_AUXILIAR_ITEM);
      invoiceItem.setNombre(product.getName());
      invoiceItem.setPrecio(String.format(Locale.US, "%.02f",
              product.getPrice()));
      invoiceItem.setDescuento(DESCUENTO);
      invoiceItem.setCodigoiva(product.getTaxProduct("iva").getTax()
              .getTypeId());
      invoiceItem.setCodigoice(COD_ICE);
      invoiceItems.add(invoiceItem);
    }

    invoice.setItems(invoiceItems);

    invoiceService.register(invoice, listener);

  }

  public void signInvoice(SigningInvoice signingInvoice,
                          RequestListener<InvoiceResponse> listener) {
    invoiceService.sign(signingInvoice, listener);
  }

  public String sequentialInvoice() {
    ForeignCollection<Invoice> list = Account.getInstance().getStore()
            .getInvoices();
    int size = list.size();
    int nextSequential = 1;
    if (size != 0) {
      int lastInvoiceId = list.size();
      CloseableIterator<Invoice> iterator = list.closeableIterator();

      Invoice invoice = new Invoice();
      while (iterator.hasNext()) {
        invoice = iterator.next();
        if (invoice.getId() == lastInvoiceId)
          break;
      }

      nextSequential = Integer.parseInt(invoice.getSecuencia()) + 1;
    }

    return String.valueOf(nextSequential);

  }

  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    customer.setCorreo(email);
    this.email = email;
  }

  public String getNames() {
    return names;
  }

  public void setNames(String names) {
    customer.setRazonsocial(names);
    this.names = names;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    Address address1 = new Address();
    address1.setCalle(address);
    customer.setLocalizacion(address1);
    customer.setDireccion(address);
    this.address = address;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    customer.setTelefono(telephoneNumber);
    this.telephoneNumber = telephoneNumber;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    customer.setIdentificacion(customerId);
    this.customerId = customerId;
  }

  public boolean isFinalConsumer() {
    return finalConsumer;
  }

  public void setFinalConsumer(boolean finalConsumer) {
    this.finalConsumer = finalConsumer;
  }

  public String getIdType() {
    return idType;
  }

  public void setIdType(String idType) {
    customer.setTipoidentificacion(idType);
    this.idType = idType;
  }
}
