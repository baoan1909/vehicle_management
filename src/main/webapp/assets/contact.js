document.addEventListener('DOMContentLoaded', function() {
    // Input focus effect
    const formControls = document.querySelectorAll('.form-control');
    
    formControls.forEach(input => {
        // Check if the input already has a value
        if (input.value) {
            input.classList.add('has-value');
        }
        
        // Handle focus event
        input.addEventListener('focus', function() {
            this.parentElement.classList.add('focused');
        });
        
        // Handle blur event
        input.addEventListener('blur', function() {
            if (!this.value) {
                this.parentElement.classList.remove('focused');
            } else {
                this.classList.add('has-value');
            }
        });
    });
    
    // Form submission
    const contactForm = document.querySelector('.form-section form');
    
    contactForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        // Change button text to indicate sending
        const submitBtn = this.querySelector('.submit-btn');
        const originalContent = submitBtn.innerHTML;
        
        submitBtn.innerHTML = '<i class="fas fa-circle-notch fa-spin btn-icon"></i> Đang gửi...';
        submitBtn.disabled = true;
        
        // Simulate form submission (replace with actual AJAX submission)
        setTimeout(() => {
            submitBtn.innerHTML = '<i class="fas fa-check-circle btn-icon"></i> Đã gửi thành công!';
            submitBtn.style.background = 'linear-gradient(135deg, #4caf50, #2e7d32)';
            submitBtn.style.boxShadow = '0 10px 20px rgba(76, 175, 80, 0.3)';
            
            // Reset form after success
            setTimeout(() => {
                contactForm.reset();
                submitBtn.innerHTML = originalContent;
                submitBtn.disabled = false;
                submitBtn.style.background = '';
                submitBtn.style.boxShadow = '';
                
                // Show success message
                const successMessage = document.createElement('div');
                successMessage.classList.add('alert', 'alert-success', 'mt-3');
                successMessage.innerHTML = '<i class="fas fa-check-circle mr-2"></i> Thông tin của bạn đã được gửi thành công. Chúng tôi sẽ liên hệ lại sớm!';
                contactForm.parentNode.insertBefore(successMessage, contactForm.nextSibling);
                
                // Remove message after 5 seconds
                setTimeout(() => {
                    successMessage.remove();
                }, 5000);
            }, 2000);
        }, 2000);
    });
});